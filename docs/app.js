import { HttpClient } from "./http.js";

// API
const urlJsonServer = "http://localhost:3000/agencias";
// DOM
const divAgencias = document.querySelector("#agencias");
const btnCadastro = document.querySelector("#btn-cadastro");
const inputNome = document.querySelector("#input-nome");
const inputRazaoSocial = document.querySelector("#input-razao-social");
const inputCnpj = document.querySelector("#input-cnpj");
const inputSituacaoCadastral = document.querySelector(
  "#input-situacao-cadastral"
);

async function carregarAgencias() {
  const res = await HttpClient.get(urlJsonServer);
  let html = `<div class="row g-3">`;
  res.forEach((agencia) => {
    html += `
        <div class="col-16 col-md-6 col-lg-4">
            <div class="card">
                <div class="card-header">
                    ID: ${agencia.id}
                </div>
                <div class="card-body">
                    <p>NOME: ${agencia.nome}</p>
                    <p>RAZÃO SOCIAL: ${agencia.razaoSocial}</p>
                    <p>CNPJ: ${agencia.cnpj}</p>
                    <p>SITUAÇÃO CADASTRAL: ${agencia.situacaoCadastral}</p>
                </div>
            </div>
        </div>
      `;
  });
  html += `</div`;
  divAgencias.innerHTML = html;
}
carregarAgencias();

async function criarAgencia() {
  const requisicao = {
    nome: inputNome.value,
    razaoSocial: inputRazaoSocial.value,
    cnpj: inputCnpj.value,
    situacaoCadastral: inputSituacaoCadastral.value,
  };
  console.log(requisicao);
  await HttpClient.post(urlJsonServer, requisicao);
  inputNome.value = "";
  inputRazaoSocial.value = "";
  inputCnpj.value = "";
  inputSituacaoCadastral.value = "ATIVO";
}

btnCadastro.addEventListener("click", criarAgencia);
