import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MatIconModule } from '@angular/material/icon';

import { AppComponent } from './app.component';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { ApiService } from './api.service';
import { PesquisaComponent } from './pesquisa/pesquisa.component';
import { SistemasComponent } from './sistemas/sistemas.component';
import { AppRoutingModule } from './/app-routing.module';

@NgModule( {
    declarations: [
        AppComponent,
        UsuariosComponent,
        PesquisaComponent,
        SistemasComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        NgbModule.forRoot(),
        MatIconModule,
        AppRoutingModule
    ],
    providers: [
        Location, {provide: LocationStrategy, useClass: PathLocationStrategy},
        ApiService
    ],
    bootstrap: [AppComponent]
} )
export class AppModule { }
