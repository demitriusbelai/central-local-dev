import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Location } from '@angular/common';
import { catchError, map, tap } from 'rxjs/operators';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { Usuario } from './usuario';
import { Sistema } from './sistema';

@Injectable()
export class ApiService {

    private apiUrl = 'api';

    constructor(
            private http: HttpClient,
            private location: Location
    ) { }

    private handleError<T> (operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            console.error(error);
            return of(result as T);
        }
    }

    public pesquisarUsuario(term: string): Observable<Usuario[]> {
        if (!term.trim()) {
            return of([]);
        }
        return this.http.get<Usuario[]>(`${this.apiUrl}/usuarios/pesquisar?q=${term}`).pipe(
                catchError(this.handleError<Usuario[]>('pesquisarUsuario', []))
        );
    }

    public buscarUsuario(id: string): Observable<Usuario> {
        return this.http.get<Usuario>(`${this.apiUrl}/usuarios/${id}`).pipe(
                catchError(this.handleError<Usuario>('buscarUsuario'))
        );
    }

    public usuario(): Observable<Usuario> {
        return this.http.get<Usuario>(`${this.apiUrl}/user`).pipe(
                catchError(this.handleError<Usuario>('buscarUsuario'))
        );
    }

    public listarSistemasUsuario(usuario: Usuario): Observable<Sistema[]> {
        return this.http.get<Sistema[]>(`${this.apiUrl}/usuarios/${usuario.identificacao}/sistemas`).pipe(
                catchError(this.handleError<Sistema[]>('listarSistemasUsuario', []))
        );
    }

    public listarSistemas(): Observable<Sistema[]> {
        //return of(MOCK_SISTEMAS);
        return this.http.get<Sistema[]>(`${this.apiUrl}/sistemas`).pipe(
                catchError(this.handleError<Sistema[]>('listarSistemasUsuario', []))
        );
    }

    public logar(usuario: Usuario) {
        this.location.go(`logar/${usuario.identificacao}`);
    }

}
