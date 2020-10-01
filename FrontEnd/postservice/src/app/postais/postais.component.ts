import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder, NgForm } from '@angular/forms';

export class Postais {
  constructor(
    public id: number,
    public  postDate: string,
    public detalhes: string,
    public pessoaid: number,
    public destinatarioid: number
  ) {
  }
}

@Component({
  selector: 'app-postais',
  templateUrl: './postais.component.html',
  styleUrls: ['./postais.component.css']
})
export class PostaisComponent implements OnInit {

  constructor(
    private httpClient: HttpClient,
    private modalService: NgbModal,
    private fb: FormBuilder
  ) { }

  postal: Postais[];
  closeResult: string;
  editForm: FormGroup;
  private deleteId: number;

  ngOnInit(): void {
    this.getUsers();
   //Editar Usuario
   this.editForm = this.fb.group({
   id: [''],
   postDate: [''],
   detalhes: [''],
   pessoaid: [''],
   destinatarioid: ['']
   });
}

//Obtener Usuarios y cargarlos en la tabla
  getUsers(){
      this.httpClient.get<any>('http://localhost:8080/postais').subscribe(
        response => {
          console.log(response);
          this.postal = response;
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
    const url = 'http://localhost:8080/postais/addNew';
    this.httpClient.post(url, f.value)
      .subscribe((result) => {
        this.ngOnInit(); //reload the table
      });
    this.modalService.dismissAll(); //dismiss the modal
  }

  //Mostrar detalles del usuario
    openDetails(targetModal, postal: Postais) {
       this.modalService.open(targetModal, {
        centered: true,
        backdrop: 'static',
        size: 'lg'
      });
      document.getElementById('postDate').setAttribute('value', postal.postDate);
        document.getElementById('detalhes').setAttribute('value', postal.detalhes);
       document.getElementById('pessoaid').setAttribute('value', postal.pessoaid.toString());
       document.getElementById('destinatarioid').setAttribute('value', postal.destinatarioid.toString());

    }

    //Mostrar editar usuario
        openEdit(targetModal, postal: Postais) {
          this.modalService.open(targetModal, {
            backdrop: 'static',
            size: 'lg'
          });
          this.editForm.patchValue( {
            id: postal.id,
            postDate: postal.postDate,
            detalhes: postal.detalhes,
            pessoaid: postal.pessoaid,
            destinatarioid: postal.destinatarioid
          });
        }

        onSave() {
          const editURL = 'http://localhost:8080/postais/' + this.editForm.value.id + '/update';
          console.log(this.editForm.value);
          this.httpClient.put(editURL, this.editForm.value)
            .subscribe((results) => {
              this.ngOnInit();
              this.modalService.dismissAll();
            });
        }

        openDelete(targetModal, postal: Postais) {
          this.deleteId = postal.id;
          this.modalService.open(targetModal, {
            backdrop: 'static',
            size: 'lg'
          });
        }

        onDelete() {
          const deleteURL = 'http://localhost:8080/postais/' + this.deleteId + '/delete';
          this.httpClient.delete(deleteURL)
            .subscribe((results) => {
              this.ngOnInit();
              this.modalService.dismissAll();
            });
        }
}
