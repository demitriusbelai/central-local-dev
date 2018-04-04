import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { of } from 'rxjs/observable/of';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';

import { Usuario } from '../usuario';
import { ApiService } from '../api.service';

@Component( {
    selector: 'app-pesquisa',
    templateUrl: './pesquisa.component.html',
    styleUrls: ['./pesquisa.component.css']
} )
export class PesquisaComponent implements OnInit {

    private searchTerms = new Subject<string>();

    usuarios: Usuario[];

    constructor(
            private apiService: ApiService
    ) { }

    ngOnInit() {
        this.searchTerms.pipe(
                debounceTime(300),
                distinctUntilChanged(),
                switchMap((term: string) => this.apiService.pesquisarUsuario(term))
        ).subscribe(usuarios => this.usuarios = usuarios);
    }

    pesquisar(term: string) {
        this.searchTerms.next(term);
    }

}
