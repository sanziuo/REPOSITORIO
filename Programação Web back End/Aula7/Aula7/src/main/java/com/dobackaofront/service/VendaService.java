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

    // ===== REGISTRAR (recebe DTO do Controller) =====
    // @Transactional: se qualquer passo falhar, NADA é salvo (rollback)
    @Transactional
    public VendaDTO registrar(VendaDTO vendaDTO) {

        // Exercício: não permite venda sem itens
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

            // Busca o produto no banco; se não existir, lança erro (e desfaz tudo)
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + itemDTO.getProdutoId()));

            VendaProduto item = new VendaProduto();
            item.setProduto(produto);                    // liga o item ao produto
            item.setQuantidade(itemDTO.getQuantidade()); // quantidade pedida
            item.setPrecoUnitario(produto.getPreco());   // preço vem do BANCO, não do cliente
            item.setVenda(venda);                        // liga o item à venda (lado dono)
            venda.getItens().add(item);                  // adiciona na lista da venda
        }

        Venda vendaSalva = registrarVenda(venda); // aplica as regras e salva
        return converterParaDTO(vendaSalva);      // devolve como DTO (dentro da transação)
    }

    // ===== REGRA DE NEGÓCIO: data, status e valor total =====
    @Transactional
    public Venda registrarVenda(Venda venda) {
        venda.setDataHora(LocalDateTime.now()); // momento do registro
        venda.setStatus(Status.PENDENTE);       // toda venda começa PENDENTE

        // Soma: preço unitário × quantidade de cada item
        double total = 0.0;
        for (VendaProduto item : venda.getItens()) {
            item.setVenda(venda);                                    // garante a ligação
            total += item.getPrecoUnitario() * item.getQuantidade(); // acumula o subtotal
        }
        venda.setValorTotal(total);

        // Salva a venda; os itens são salvos junto por causa do cascade = ALL
        return vendaRepository.save(venda);
    }

    // ===== LISTAR TODAS =====
    @Transactional(readOnly = true) // só leitura; mantém a sessão aberta para ler os itens
    public List<VendaDTO> listarTodas() {
        return vendaRepository.findAll().stream()
                .map(this::converterParaDTO)
                .toList();
    }

    // ===== BUSCAR UMA =====
    @Transactional(readOnly = true)
    public VendaDTO buscarPorId(Long id) {
        return converterParaDTO(buscarEntidade(id));
    }

    // ===== EXERCÍCIO: CANCELAR (não apaga, só muda o status) =====
    @Transactional
    public VendaDTO cancelarVenda(Long id) {
        Venda venda = buscarEntidade(id);
        venda.setStatus(Status.CANCELADA);                 // altera o status
        return converterParaDTO(vendaRepository.save(venda)); // salva a alteração
    }

    // ===== ALTERAR STATUS (ex.: EM_PREPARO, CONCLUIDA) =====
    @Transactional
    public VendaDTO alterarStatus(Long id, Status novoStatus) {
        Venda venda = buscarEntidade(id);
        venda.setStatus(novoStatus);
        return converterParaDTO(vendaRepository.save(venda));
    }

    // Busca a entidade Venda ou lança erro se não existir
    private Venda buscarEntidade(Long id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada: " + id));
    }

    // Converte entidade Venda → VendaDTO (com os itens)
    private VendaDTO converterParaDTO(Venda venda) {
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