import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { Cliente } from '../cliente';
import { ClientesService } from '../../clientes.service';
import { Observable } from 'rxjs';
@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesFormComponent implements OnInit {

  // cliente: Cliente;
  // //service: ClientesService;

  // constructor( private service: ClientesService) {
  //   //this.cliente = service.getCliente();
  //   this.cliente = new Cliente();
  // }

  // ngOnInit(): void {
  // }
  //
  // onSubmit(){
  //   //console.log(this.cliente);
  //   this.service
  //     .salvar(this.cliente)
  //     .subscribe( response => {
  //       console.log(response);
  //     });
  // }


  cliente: Cliente;
  success = false;
  // tslint:disable-next-line: ban-types
  errors: String[];
  id: number;

  constructor(
      private service: ClientesService ,
      private router: Router,
      private activatedRoute: ActivatedRoute
      ) {
    this.cliente = new Cliente();
  }

  ngOnInit(): void {
    let params : Observable<Params> = this.activatedRoute.params;
    params.subscribe( urlParams => {
        this.id = urlParams['id'];
        if(this.id){
          this.service
            .getClienteById(this.id)
            .subscribe(
              response => this.cliente = response ,
              errorResponse => this.cliente = new Cliente()
            )
        }
    })
  }

  // tslint:disable-next-line: typedef
  voltarParaListagem(){
    this.router.navigate(['/clientes-lista'])
  }

  // tslint:disable-next-line: typedef
  onSubmit(){
    if (this.id){
      this.service
        .atualizar(this.cliente)
        .subscribe(response => {
          this.success = true;
          this.errors = null;
        }, errorResponse => {
          //console.log(errorResponse.error.errors),
          this.errors = ['Erro ao atualizar o cliente.'];
        });

      //console.log(response);  //Faz com que aparece no console o id e a dataCadastro
      //this.cliente = response; //Faz com que aparece na tela o id e a dataCadastro


    }else{

      this.service
        .salvar(this.cliente)
          .subscribe( response => {
            this.success = true;
            this.errors = null;
            this.cliente = response; //Faz com que aparece na tela o id e a dataCadastro, após finalizar o HTTP PUT
          } , errorResponse => {
            this.success = false;
            this.errors = errorResponse.error.errors;
          });

    }

  }

}
