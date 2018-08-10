import { Observable, of } from 'rxjs';
import {catchError, debounceTime, distinctUntilChanged, map, tap, switchMap} from 'rxjs/operators';
import { JobStatus } from '../models/job-status';
import { Component, OnInit, Input } from '@angular/core';
import { Job } from '../models/job';
import { Board } from '../models/board';
import { JobService } from '../job.service';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { NgForm } from '@angular/forms';
import { JobStatusService } from '../job-status.service';
import { Company } from '../models/company';
import { GoogleKnowledgeGraphService } from '../google-knowledge-graph.service';

@Component({
  selector: 'app-job-list',
  templateUrl: './job-list.component.html',
  styleUrls: ['./job-list.component.css']
})
export class JobListComponent implements OnInit {
  @Input() board: Board;
  jobs: Job[] = [];
  statuses: JobStatus[] = [];
  selectedJob: Job = null;
  closeResult: string;
  editJob: Job = null;
  selectedStatus = '';
  public model: any;

  getStatus(job: Job) {
    switch (job.status.status) {
      case 'Interested': return 'interested';
      case 'Applied': return 'applied';
      case 'Interviewing': return 'interviewing';
      case 'Offered': return 'offered';
      case 'Rejected': return 'rejected';
      case 'No Reply': return 'noReply';
    }
  }

  loadJobs() {
    this.jobService.index(this.board.id).subscribe(
      (data) => this.jobs = data,
      (err) => console.log(err)
    );
  }

  loadJobStatus() {
    this.jobStatusService.index().subscribe(
      (data) => this.statuses = data,
      (err) => console.log(err)
    );
  }

  createJob(form: NgForm) {
    const job = new Job();
    job.title = form.value.title;

    const company = new Company();
    company.name = form.value.companyName;

    let jobStatus = new JobStatus();

    this.statuses.forEach( (status: JobStatus) => {
      if (status.status === form.value.jobStatus) {
        jobStatus = status;
      }
    });

    job.company = company;
    job.status = jobStatus;

    this.jobService.create(job, this.board.id).subscribe(
      (data) => this.loadJobs(),
      (err) => console.log(err)
    );
  }

  updateJob (form: NgForm) {
    const job = new Job();
    job.title = form.value.title;
    job.salary = form.value.salary;
    job.postUrl = form.value.postUrl;
    job.applicationDate = form.value.applicationDate;
    job.offerDate = form.value.offerDate;
    job.description = form.value.description;
    let jobStatus = new JobStatus();

    this.statuses.forEach( (status: JobStatus) => {
      if (status.status === form.value.jobStatus) {
        jobStatus = status;
      }
    });
    job.status = jobStatus;

    this.jobService.update(job, this.editJob.id, this.board.id).subscribe(
      (data) => {
        this.selectedJob = data;
        this.editJob = null;
        this.loadJobs();
      },
      (err) => console.log(err)
    );
  }

  deleteJob(bid: number, jid: number) {
    this.jobService.destroy(bid, jid).subscribe(
      (data) => {
        this.loadJobs();
      },
      (err) => console.log(err)
    );
  }

  // google api call
  searchKnowledgeGraph(phrase: string) {
    console.log(phrase);
  }

  search = (text$: Observable<string>) =>
    text$.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap(term =>
        this.gkgService.search(term).pipe(
          map((data) => {
            console.log(data);

            const mappedResults = [];
            data.itemListElement.forEach(function(element) {
              if (element.result['@type'].indexOf('Corporation') !== -1 || element['@type'].indexOf('Organization') !== -1) {
                mappedResults.push(element.result.name);
              }
            });
            return mappedResults;
          }),
          catchError((err) => {
            console.log(err);
            return of([]);
          })
        )
      ),
    )

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

  constructor(private jobService: JobService,
              private modalService: NgbModal,
              private jobStatusService: JobStatusService,
              private gkgService: GoogleKnowledgeGraphService) {}

  ngOnInit() {
    this.loadJobs();
    this.loadJobStatus();
  }

}
