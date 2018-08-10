import { NoteService } from '../note.service';
import { Component, OnInit, Input } from '@angular/core';
import { Note } from '../models/note';
import { Job } from '../models/job';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-note-list',
  templateUrl: './note-list.component.html',
  styleUrls: ['./note-list.component.css']
})
export class NoteListComponent implements OnInit {
  @Input() job: Job;
  notes: Note[] = [];
  closeResult: string;
  editNote: Note = null;

  loadNotes(jid) {
    this.noteService.index(jid).subscribe(
      (data) => this.notes = data,
      (err) => console.log(err)
    );
  }

  createNote(form: NgForm) {
    const note = new Note();
    note.content = form.value.content;

    this.noteService.create(note, this.job.id).subscribe(
      (data) => this.loadNotes(this.job.id),
      (err) => console.log(err)
    );
  }

  updateNote(form: NgForm) {
    const note = new Note();
    note.id = this.editNote.id;
    note.content = form.value.content;

    this.noteService.update(note, this.job.id).subscribe(
      (data) => {
        this.loadNotes(this.job.id);
        this.editNote = null;
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

  constructor(private noteService: NoteService,
              private modalService: NgbModal) { }

  ngOnInit() {
    this.loadNotes(this.job.id);
  }

}
