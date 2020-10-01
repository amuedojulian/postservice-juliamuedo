import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder, NgForm } from '@angular/forms';

export class Destinatarios {
  constructor(
    public id: number,
    public nome: string,
    public sobrenome: string,
    public email: string
  ) {
  }
}

@Component({
  selector: 'app-destinatarios',
  templateUrl: './destinatarios.component.html',
  styleUrls: ['./destinatarios.component.css']
})
export class DestinatariosComponent implements OnInit {

  constructor(
    private httpClient: HttpClient,
    private modalService: NgbModal,
    private fb: FormBuilder
  ) { }

  destinatario: Destinatarios[];
  closeResult: string;
  editForm: FormGroup;
  private deleteId: number;

  ngOnInit(): void {
      this.getUsers();
     //Editar Usuario
     this.editForm = this.fb.group({
      id: [''],
      nome: [''],
      sobrenome: [''],
      email: [''],
     });
  }

  //Obtener Usuarios y cargarlos en la tabla
    getUsers(){
        this.httpClient.get<any>('http://localhost:8080/destinatarios').subscribe(
          response => {
            console.log(response);
            this.destinatario = response;
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
      const url = 'http://localhost:8080/destinatarios/addNew';
      this.httpClient.post(url, f.value)
        .subscribe((result) => {
          this.ngOnInit(); //reload the table
        });
      this.modalService.dismissAll(); //dismiss the modal
    }

    //Mostrar detalles del usuario
      openDetails(targetModal, destinatario: Destinatarios) {
         this.modalService.open(targetModal, {
          centered: true,
          backdrop: 'static',
          size: 'lg'
        });
         document.getElementById('name').setAttribute('value', destinatario.nome);
         document.getElementById('sobrenome').setAttribute('value', destinatario.sobrenome);
         document.getElementById('mail').setAttribute('value', destinatario.email);

      }

      //Mostrar editar usuario
          openEdit(targetModal, destinatario: Destinatarios) {
            this.modalService.open(targetModal, {
              backdrop: 'static',
              size: 'lg'
            });
            this.editForm.patchValue( {
              id: destinatario.id,
              nome: destinatario.nome,
              sobrenome: destinatario.sobrenome,
              email: destinatario.email,
            });
          }

          onSave() {
            const editURL = 'http://localhost:8080/destinatarios/' + this.editForm.value.id + '/update';
            console.log(this.editForm.value);
            this.httpClient.put(editURL, this.editForm.value)
              .subscribe((results) => {
                this.ngOnInit();
                this.modalService.dismissAll();
              });
          }

          openDelete(targetModal, destinatario: Destinatarios) {
            this.deleteId = destinatario.id;
            this.modalService.open(targetModal, {
              backdrop: 'static',
              size: 'lg'
            });
          }

          onDelete() {
            const deleteURL = 'http://localhost:8080/destinatarios/' + this.deleteId + '/delete';
            this.httpClient.delete(deleteURL)
              .subscribe((results) => {
                this.ngOnInit();
                this.modalService.dismissAll();
              });
          }

}
