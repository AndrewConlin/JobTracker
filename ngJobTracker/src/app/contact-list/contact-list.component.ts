import { Component, OnInit, Input } from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { Job } from '../models/job';
import { Contact } from '../models/contact';
import { NgForm } from '@angular/forms';
import { ContactService } from '../contact.service';

@Component({
  selector: 'app-contact-list',
  templateUrl: './contact-list.component.html',
  styleUrls: ['./contact-list.component.css']
})
export class ContactListComponent implements OnInit {
  @Input() job: Job;
  contacts: Contact[] = [];
  closeResult: string;
  editContact: Contact = null;

  loadContacts(jid: number) {
    this.contactService.index(jid).subscribe(
      (data) => this.contacts = data,
      (err) => console.log(err)
    );
  }

  createContact(form: NgForm) {
    const contact = new Contact();
    contact.firstName = form.value.firstName;
    contact.lastName = form.value.lastName;
    contact.position = form.value.position;
    contact.email = form.value.email;
    contact.phoneNumber = form.value.phoneNumber;
    contact.description = form.value.description;

    this.contactService.create(contact, this.job.id).subscribe(
      (data) => this.loadContacts(this.job.id),
      (err) => console.log(err)
    );
  }

  updateContact(form: NgForm) {
    const contact = new Contact();
    contact.id = this.editContact.id;
    contact.firstName = form.value.firstName;
    contact.lastName = form.value.lastName;
    contact.position = form.value.position;
    contact.email = form.value.email;
    contact.phoneNumber = form.value.phoneNumber;
    contact.description = form.value.description;

    this.contactService.create(contact, this.job.id).subscribe(
      (data) => {
        this.loadContacts(this.job.id);
        this.editContact = null;
      },
      (err) => console.log(err)
    );
  }

  // modal methods
  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }
  constructor(private contactService: ContactService, private modalService: NgbModal) { }

  ngOnInit() {
    this.loadContacts(this.job.id);
  }

}
