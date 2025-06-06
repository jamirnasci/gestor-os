export default interface Ordem {
    idordem: number;
    tipo: string;
    data_entrega: string;
    status: string;
    preco: string;
    produto_idproduto: number;
}