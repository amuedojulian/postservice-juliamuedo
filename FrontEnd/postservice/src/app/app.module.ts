import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { PessoasComponent } from './pessoas/pessoas.component';
import { PostaisComponent } from './postais/postais.component';
import { LugaresComponent } from './lugares/lugares.component';
import { DestinatariosComponent } from './destinatarios/destinatarios.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PessoasComponent,
    PostaisComponent,
    LugaresComponent,
    DestinatariosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
