import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder, NgForm } from '@angular/forms';

export class Pessoas {
  constructor(
    public id: number,
    public nome: string,
    public apellido: string,
    public lugarid: number,
    public email: string
  ) {
  }
}

@Component({
  selector: 'app-pessoas',
  templateUrl: './pessoas.component.html',
  styleUrls: ['./pessoas.component.css']
})
export class PessoasComponent implements OnInit {

  constructor(
    private httpClient: HttpClient,
    private modalService: NgbModal,
    private fb: FormBuilder
  ) { }

  pessoa: Pessoas[];
  closeResult: string;
  editForm: FormGroup;
  private deleteId: number;

  ngOnInit(): void {
     this.getUsers();
     //Editar Usuario
     this.editForm = this.fb.group({
      id: [''],
      nome: [''],
      apellido: [''],
      lugarid: [''],
      email: [''],
     });
  }

  //Obtener Usuarios y cargarlos en la tabla
  getUsers(){
      this.httpClient.get<any>('http://localhost:8080/pessoas').subscribe(
        response => {
          console.log(response);
          this.pessoa = response;
        }
      );
    }

  //Abrir Popup Modal
    open(content) {
      this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
        this.closeResult = `Closed with: ${result}`;
      }, (reason) => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      });
    }

    //Cerrar Popup Modal
    private getDismissReason(reason: any): string {
      if (reason === ModalDismissReasons.ESC) {
        return 'by pressing ESC';
      } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
        return 'by clicking on a backdrop';
      } else {
        return `with: ${reason}`;
      }
    }

  //Añadir nuevo usuario
    onSubmit(f: NgForm) {
      const url = 'http://localhost:8080/pessoas/addNew';
      this.httpClient.post(url, f.value)
        .subscribe((result) => {
          this.ngOnInit(); //reload the table
        });
      this.modalService.dismissAll(); //dismiss the modal
    }

    //Mostrar detalles del usuario
      openDetails(targetModal, pessoa: Pessoas) {
         this.modalService.open(targetModal, {
          centered: true,
          backdrop: 'static',
          size: 'lg'
        });
         document.getElementById('name').setAttribute('value', pessoa.nome);
         document.getElementById('sobrenome').setAttribute('value', pessoa.apellido);
         document.getElementById('locationid').setAttribute('value', pessoa.lugarid.toString());
         document.getElementById('mail').setAttribute('value', pessoa.email);

      }

      //Mostrar editar usuario
          openEdit(targetModal, pessoa: Pessoas) {
            this.modalService.open(targetModal, {
              backdrop: 'static',
              size: 'lg'
            });
            this.editForm.patchValue( {
              id: pessoa.id,
              nome: pessoa.nome,
              apellido: pessoa.apellido,
              lugarid: pessoa.lugarid,
              email: pessoa.email,
            });
          }

          onSave() {
            const editURL = 'http://localhost:8080/pessoas/' + this.editForm.value.id + '/update';
            console.log(this.editForm.value);
            this.httpClient.put(editURL, this.editForm.value)
              .subscribe((results) => {
                this.ngOnInit();
                this.modalService.dismissAll();
              });
          }

          openDelete(targetModal, pessoa: Pessoas) {
            this.deleteId = pessoa.id;
            this.modalService.open(targetModal, {
              backdrop: 'static',
              size: 'lg'
            });
          }

          onDelete() {
            const deleteURL = 'http://localhost:8080/pessoas/' + this.deleteId + '/delete';
            this.httpClient.delete(deleteURL)
              .subscribe((results) => {
                this.ngOnInit();
                this.modalService.dismissAll();
              });
          }
}
