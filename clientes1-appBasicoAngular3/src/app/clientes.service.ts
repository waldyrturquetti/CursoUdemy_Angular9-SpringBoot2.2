import { Injectable } from '@angular/core';
import { Cliente } from './clientes/cliente';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';    //Tranforma a apliação em uma aplicação reativa

// @Injectable({
//   providedIn: 'root'
// })
// export class ClientesService {

//   constructor(private http : HttpClient) {
//   }

//   salvar( cliente: Cliente ) : Observable<Cliente> {   //O Observable não deixa ser uma Requisição Assíncrona
//     return this.http.post<Cliente>('http://localhost:8080/api/clientes', cliente);
//   }

//   getCliente() : Cliente {
//     let cliente : Cliente = new Cliente();
//     cliente.nome = 'Waldyrzao';
//     cliente.cpf = '69696969';

//     return cliente;
//   }

// }

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  constructor( private http: HttpClient ) {}

  salvar( cliente: Cliente ) : Observable<Cliente> {
    return this.http.post<Cliente>('http://localhost:8080/api/clientes' , cliente);
  }

  atualizar( cliente: Cliente ) : Observable<any> {
    return this.http.put<Cliente>(`http://localhost:8080/api/clientes/${cliente.id}` , cliente);
  }

  getClientes() : Observable<Cliente[]> {
    return this.http.get<Cliente[]>('http://localhost:8080/api/clientes');
  }

  // getClientes(): Cliente[] {
  //   let cliente: Cliente = new Cliente();
  //   cliente.id = 1;
  //   cliente.nome = 'Waldyrzao';
  //   cliente.cpf = '69696969';
  //   cliente.dataCadastro = '18/04/2020';
  //   return [cliente];
  // }

  getClienteById(id: number) : Observable<Cliente> {
    return this.http.get<any>(`http://localhost:8080/api/clientes/${id}`);
  }

  deletar(cliente: Cliente) : Observable<any> {
    return this.http.delete<any>(`http://localhost:8080/api/clientes/${cliente.id}`);
  }

}
