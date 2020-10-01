import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder, NgForm } from '@angular/forms';

export class Lugares {
  constructor(
    public id: number,
    public nome: string,
  ) {
  }
}

@Component({
  selector: 'app-lugares',
  templateUrl: './lugares.component.html',
  styleUrls: ['./lugares.component.css']
})
export class LugaresComponent implements OnInit {

  constructor(
    private httpClient: HttpClient,
    private modalService: NgbModal,
    private fb: FormBuilder
  ) { }

  lugar: Lugares[];
  closeResult: string;
  editForm: FormGroup;
  private deleteId: number;

  ngOnInit(): void {
    this.getUsers();
   //Editar Usuario
   this.editForm = this.fb.group({
    id: [''],
    nome: ['']
   });
}

//Obtener Usuarios y cargarlos en la tabla
  getUsers(){
      this.httpClient.get<any>('http://localhost:8080/lugares').subscribe(
        response => {
          console.log(response);
          this.lugar = response;
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
    const url = 'http://localhost:8080/lugares/addNew';
    this.httpClient.post(url, f.value)
      .subscribe((result) => {
        this.ngOnInit(); //reload the table
      });
    this.modalService.dismissAll(); //dismiss the modal
  }

  //Mostrar detalles del usuario
    openDetails(targetModal, lugar: Lugares) {
       this.modalService.open(targetModal, {
        centered: true,
        backdrop: 'static',
        size: 'lg'
      });
       document.getElementById('name').setAttribute('value', lugar.nome);

    }

    //Mostrar editar usuario
        openEdit(targetModal, lugar: Lugares) {
          this.modalService.open(targetModal, {
            backdrop: 'static',
            size: 'lg'
          });
          this.editForm.patchValue( {
            id: lugar.id,
            nome: lugar.nome
          });
        }

        onSave() {
          const editURL = 'http://localhost:8080/lugares/' + this.editForm.value.id + '/update';
          console.log(this.editForm.value);
          this.httpClient.put(editURL, this.editForm.value)
            .subscribe((results) => {
              this.ngOnInit();
              this.modalService.dismissAll();
            });
        }

        openDelete(targetModal, lugar: Lugares) {
          this.deleteId = lugar.id;
          this.modalService.open(targetModal, {
            backdrop: 'static',
            size: 'lg'
          });
        }

        onDelete() {
          const deleteURL = 'http://localhost:8080/lugares/' + this.deleteId + '/delete';
          this.httpClient.delete(deleteURL)
            .subscribe((results) => {
              this.ngOnInit();
              this.modalService.dismissAll();
            });
        }

}
