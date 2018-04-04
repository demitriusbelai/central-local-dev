import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PesquisaComponent } from './pesquisa/pesquisa.component';
import { SistemasComponent } from './sistemas/sistemas.component';

const routes: Routes = [
    { path: '', component: PesquisaComponent },
    { path: 'sistemas', component: SistemasComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ],
})
export class AppRoutingModule { }
