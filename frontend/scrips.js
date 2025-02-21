document.getElementById("produtoForm").addEventListener("submit", function(event) {
    event.preventDefault();
    const nome = document.getElementById("nome").value;
    const preco = document.getElementById("preco").value;

    fetch("http://localhost:8080/produtos", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ nome, preco })
    }).then(response => response.json())
      .then(() => {
          carregarProdutos();  
          document.getElementById("nome").value = "";  
          document.getElementById("preco").value = ""; 
      })
      .catch(error => alert("Erro ao adicionar produto: " + error));
});

function carregarProdutos() {
    
    fetch("http://localhost:8080/produtos")
        .then(response => response.json())
        .then(produtos => {
            const tabela = document.getElementById("tabelaProdutos");
            tabela.innerHTML = ""; 

            produtos.forEach(produto => {
                const row = `<tr>
                    <td>${produto.id}</td>
                    <td>${produto.nome}</td>
                    <td>R$ ${parseFloat(produto.preco).toFixed(2)}</td>
                    <td>
                        <button onclick="excluirProduto(${produto.id})">Excluir</button>
                    </td>
                </tr>`;
                tabela.innerHTML += row; 
            });
        })
        .catch(error => alert("Erro ao carregar produtos: " + error));
}

function excluirProduto(id) {

    fetch(`http://localhost:8080/produtos/${id}`, { method: "DELETE" })
        .then(() => carregarProdutos()) 
        .catch(error => alert("Erro ao excluir produto: " + error));
}

carregarProdutos(); 
