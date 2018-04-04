import { Component, OnInit } from '@angular/core';

import { Usuario } from '../usuario';
import { Sistema } from '../sistema';
import { ApiService } from '../api.service';

@Component( {
    selector: 'app-usuario',
    templateUrl: './sistemas.component.html',
    styleUrls: ['./sistemas.component.css']
} )
export class SistemasComponent implements OnInit {

    usuario: Usuario;
    sistemas: Sistema[] = [];

    constructor(
        private apiService: ApiService
    ) { }

    ngOnInit() {
        this.getUsuario();
    }

    getUsuario(): void {
        this.apiService.usuario()
            .subscribe( usuario => this.usuario = usuario );
        this.apiService.listarSistemas()
            .subscribe( sistemas => this.sistemas = sistemas )

    }

}
