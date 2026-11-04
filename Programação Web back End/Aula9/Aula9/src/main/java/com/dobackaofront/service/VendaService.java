package com.dobackaofront.service; // pacote das regras de negócio

import com.dobackaofront.dto.VendaDTO;                          // DTO da venda
import com.dobackaofront.dto.VendaProdutoDTO;                   // DTO do item
import com.dobackaofront.model.Produto;                         // entidade Produto
import com.dobackaofront.model.Status;                          // enum de status
import com.dobackaofront.model.Venda;                           // entidade Venda
import com.dobackaofront.model.VendaProduto;                    // entidade item da venda
import com.dobackaofront.repository.ProdutoRepository;          // acesso a produtos
import com.dobackaofront.repository.VendaRepository;            // acesso a vendas
import org.springframework.beans.factory.annotation.Autowired;  // injeção de dependência
import org.springframework.data.domain.Sort;                    // ordenação
import org.springframework.stereotype.Service;                  // anotação de serviço
import org.springframework.transaction.annotation.Transactional; // transação (tudo ou nada)

import java.time.LocalDateTime; // data e hora
import java.util.List;          // lista

// Serviço de vendas: concentra TODAS as regras de negócio da venda
@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;     // salva e busca vendas

    @Autowired
    private ProdutoRepository produtoRepository; // busca produtos (e o preço atual)

    // Ordenação padrão: vendas mais recentes primeiro (ORDER BY data_hora DESC)
    private static final Sort MAIS_RECENTES = Sort.by(Sort.Direction.DESC, "dataHora");

    // =====================================================================
    // MÉTODOS PARA AS TELAS (Capítulo 8) – devolvem ENTIDADES para o Thymeleaf
    // =====================================================================

    // Lista todas as vendas, mais recentes primeiro
    public List<Venda> listarTodas() {
        return vendaRepository.findAll(MAIS_RECENTES);
    }

    // Lista só as vendas de um status (filtro da tela)
    public List<Venda> listarPorStatus(Status status) {
        return vendaRepository.findByStatus(status, MAIS_RECENTES);
    }

    // Busca uma venda pelo id; lança erro se não existir
    public Venda buscarPorId(Long id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada: " + id));
    }

    // Resumo: quantas vendas existem em um status
    public long contarPorStatus(Status status) {
        return vendaRepository.countByStatus(status);
    }

    // Resumo: soma do valor das vendas de um status
    public Double somarPorStatus(Status status) {
        return vendaRepository.somarValorPorStatus(status);
    }

    // =====================================================================
    // REGISTRO DE VENDA (Capítulo 7)
    // =====================================================================

    // Recebe o DTO do Controller, monta a venda e salva.
    // @Transactional: se qualquer passo falhar, NADA é salvo (rollback)
    @Transactional
    public VendaDTO registrar(VendaDTO vendaDTO) {

        // Não permite venda sem itens
        if (vendaDTO.getItens() == null || vendaDTO.getItens().isEmpty()) {
            throw new RuntimeException("A venda precisa ter pelo menos um item.");
        }

        Venda venda = new Venda(); // venda nova, ainda sem itens

        // Converte cada item do DTO em uma entidade VendaProduto
        for (VendaProdutoDTO itemDTO : vendaDTO.getItens()) {

            // Valida a quantidade
            if (itemDTO.getQuantidade() == null || itemDTO.getQuantidade() <= 0) {
                throw new RuntimeException("Quantidade inválida para o produto " + itemDTO.getProdutoId());
            }

            // Busca o produto; se não existir, lança erro (e desfaz tudo)
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + itemDTO.getProdutoId()));

            VendaProduto item = new VendaProduto();
            item.setProduto(produto);                    // liga o item ao produto
            item.setQuantidade(itemDTO.getQuantidade()); // quantidade pedida
            item.setPrecoUnitario(produto.getPreco());   // preço vem do BANCO, não do cliente
            item.setVenda(venda);                        // liga o item à venda
            venda.getItens().add(item);                  // adiciona na lista da venda
        }

        Venda vendaSalva = registrarVenda(venda); // aplica as regras e salva
        return converterParaDTO(vendaSalva);      // devolve como DTO
    }

    // Regra de negócio: data, status inicial e valor total
    @Transactional
    public Venda registrarVenda(Venda venda) {
        venda.setDataHora(LocalDateTime.now()); // momento do registro
        venda.setStatus(Status.PENDENTE);       // toda venda começa PENDENTE

        // Soma: preço unitário × quantidade de cada item
        double total = 0.0;
        for (VendaProduto item : venda.getItens()) {
            item.setVenda(venda);
            total += item.getPrecoUnitario() * item.getQuantidade();
        }
        venda.setValorTotal(total);

        // Salva a venda; os itens vão junto (cascade = ALL)
        return vendaRepository.save(venda);
    }

    // =====================================================================
    // ALTERAÇÃO DE STATUS
    // =====================================================================

    // Cancela: muda o status para CANCELADA (não apaga o registro)
    @Transactional
    public Venda cancelarVenda(Long id) {
        Venda venda = buscarPorId(id);
        venda.setStatus(Status.CANCELADA);
        return vendaRepository.save(venda);
    }

    // Muda para qualquer status (PENDENTE, EM_PREPARO, CONCLUIDA, CANCELADA)
    @Transactional
    public Venda alterarStatus(Long id, Status novoStatus) {
        Venda venda = buscarPorId(id);
        venda.setStatus(novoStatus);
        return vendaRepository.save(venda);
    }

    // =====================================================================
    // MÉTODOS PARA A API (devolvem DTO em JSON)
    // =====================================================================

    // Lista todas como DTO
    @Transactional(readOnly = true)
    public List<VendaDTO> listarTodasDTO() {
        return listarTodas().stream().map(this::converterParaDTO).toList();
    }

    // Busca uma como DTO
    @Transactional(readOnly = true)
    public VendaDTO buscarPorIdDTO(Long id) {
        return converterParaDTO(buscarPorId(id));
    }

    // Converte entidade Venda → VendaDTO (com os itens)
    @Transactional(readOnly = true)
    public VendaDTO converterParaDTO(Venda venda) {
        VendaDTO dto = new VendaDTO();
        dto.setId(venda.getId());
        dto.setDataHora(venda.getDataHora());
        dto.setStatus(venda.getStatus().name()); // enum → texto
        dto.setValorTotal(venda.getValorTotal());

        // Converte cada item
        List<VendaProdutoDTO> itensDTO = venda.getItens().stream().map(item -> {
            VendaProdutoDTO i = new VendaProdutoDTO();
            i.setProdutoId(item.getProduto().getId());
            i.setNomeProduto(item.getProduto().getNome());
            i.setQuantidade(item.getQuantidade());
            i.setPrecoUnitario(item.getPrecoUnitario());
            i.setSubtotal(item.getPrecoUnitario() * item.getQuantidade());
            return i;
        }).toList();

        dto.setItens(itensDTO);
        return dto;
    }
}