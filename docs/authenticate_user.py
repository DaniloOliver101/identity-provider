import requests
import json 

def authenticate(username, password):
    url = "http://localhost:9000/api/v1/auth/authenticate"
    payload = {
        "username": username,
        "password": password
    }
    headers = {
        "Content-Type": "application/json"
    }
    response = requests.post(url, json=payload, headers=headers)
    if response.ok:
        print("Token recebido:", response.text)
    else:
        print("Erro na autenticação:", response.status_code, response.text)

if __name__ == "__main__":
    # Substitua 'seu_usuario' e 'sua_senha' pelos valores desejados
    authenticate("danilo", "minhasenha")
