estado_inicial = 25
estado_final = 26

tabela_transicao = {
    (25, True): 26,
    (26, True): 26,
    # (26, False): 27
}

cadeia = list(input("Digite uma sequencia: ").strip())
estado = estado_inicial

for i in range(len(cadeia)):
    isDigit = cadeia[i] in "0123456789"
    proximo_estado = tabela_transicao.get(estado, isDigit)
    
    estado = proximo_estado
    
    if not estado:
        print(f"{''.join(cadeia)} não é um número inteiro.")
        exit()
        
print(f"{''.join(cadeia)} é um número inteiro")