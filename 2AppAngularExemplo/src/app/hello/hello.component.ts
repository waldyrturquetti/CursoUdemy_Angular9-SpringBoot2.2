import { Component } from '@angular/core'

@Component({
    selector: 'hello',
    templateUrl: './hello.component.html'
    
    //template: `
    //    <p>Hello World</p>
    //`
})
export class HelloComponent {

    //nome : string = 'Waldyrzao';

    nome : string
    clientes: Cliente[];

    constructor(){
        this.nome = 'Waldyrzao';
        let fulado = new Cliente('Waldyrzao', 20);
        let cicrano = new Cliente('WaldyrLindo', 20);

        this.clientes = [fulado, cicrano];
    }
}

class Cliente{
    constructor(
        public nome: string,
        public idade: number
    ){}
}