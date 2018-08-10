import { FileLocation } from './../models/file-location';
import { Component, OnInit, Input } from '@angular/core';
import { Job } from '../models/job';
import { FileService } from '../file.service';
import { NgForm } from '../../../node_modules/@angular/forms';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-file-list',
  templateUrl: './file-list.component.html',
  styleUrls: ['./file-list.component.css']
})
export class FileListComponent implements OnInit {
  @Input() jobId: number;
  files: FileLocation[] = [];
  closeResult: string;
  fileToUpload = null;
  fileUploading = false;

  loadFiles(jid: number) {
    if (!jid) {
      this.fileService.getAllFiles().subscribe(
        (data) => this.files = data,
        (err) => console.log(err)
      );
    } else {
      this.fileService.getAllFilesForJob(jid).subscribe(
        (data) => this.files = data,
        (err) => console.log(err)
      );
    }
  }

  uploadFile(form: NgForm) {
    this.fileUploading = true;
    const obj: any = {name: form.value.name, description: form.value.description};
    if (this.jobId) {
      obj.jobId = this.jobId;
    }

    this.fileService.upload(this.fileToUpload, obj).subscribe(
      (data) => {
        this.fileUploading = false;
        this.loadFiles(this.jobId);
      },
      (err) => console.log(err)
    );
  }

  fileEvent(fileInput: Event) {
    // @ts-ignore
    this.fileToUpload = fileInput.target.files[0];
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

  constructor(private fileService: FileService, private modalService: NgbModal) { }

  ngOnInit() {
    this.loadFiles(this.jobId);
  }
}
