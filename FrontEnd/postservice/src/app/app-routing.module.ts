import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { PessoasComponent } from './pessoas/pessoas.component';
import { PostaisComponent } from './postais/postais.component';
import { LugaresComponent } from './lugares/lugares.component';
import { DestinatariosComponent } from './destinatarios/destinatarios.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},
  {path: 'postais', component: PostaisComponent},
  {path: 'pessoas', component: PessoasComponent},
  {path: 'lugares', component: LugaresComponent},
  {path: 'destinatarios', component: DestinatariosComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
