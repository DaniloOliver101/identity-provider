import requests

def register_user(username, password):
    url = "http://localhost:9000/api/v1/auth/register"
    payload = {
        "username": username,
        "password": password
    }
    headers = {
        "Content-Type": "application/json"
    }
    response = requests.post(url, json=payload, headers=headers)
    if response.ok:
        print("Usuário registrado com sucesso:", response.text)
    else:
        print("Erro ao registrar usuário:", response.status_code, response.text)

if __name__ == "__main__":
    # Substitua 'novo_usuario' e 'sua_senha' pelos valores desejados
    register_user("danilo", "minhasenha")
