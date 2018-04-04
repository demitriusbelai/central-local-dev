import { Component, OnInit, Input, ViewChild, ElementRef } from '@angular/core';
import { Usuario } from '../usuario';

@Component( {
    selector: 'app-usuarios',
    templateUrl: './usuarios.component.html',
    styleUrls: ['./usuarios.component.css']
} )
export class UsuariosComponent implements OnInit {

    urlLogin = '/login';

    //selecionado: Usuario;

    @Input() usuarios: Usuario[] = [];

    @ViewChild('formLogin')
    private form: ElementRef;

    constructor() { }

    ngOnInit() {
    }

    selecionar( usuario: Usuario ) {
        //this.selecionado = usuario;
        this.form.nativeElement.elements['username'].value = usuario.identificacao;
        this.form.nativeElement.submit();
    }

}
