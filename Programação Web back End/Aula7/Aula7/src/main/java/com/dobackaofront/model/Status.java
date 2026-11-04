package com.dobackaofront.model; // pacote das entidades

// Enum: conjunto FIXO de valores possíveis para o status de uma venda.
// Impede valores inválidos como "Finalizada" ou "pendente".
public enum Status {
    PENDENTE,    // venda registrada, aguardando preparo
    EM_PREPARO,  // pedido sendo preparado na cozinha
    CONCLUIDA,   // pedido entregue/pago
    CANCELADA    // pedido cancelado (registro continua no banco)
}