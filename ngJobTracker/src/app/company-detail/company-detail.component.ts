import { ContactService } from '../contact.service';
import { Company } from '../models/company';
import { Component, OnInit, Input } from '@angular/core';
import { Job } from '../models/job';
import { NgForm } from '@angular/forms';
import { CompanyService } from '../company.service';
import { Address } from '../models/address';

@Component({
  selector: 'app-company-detail',
  templateUrl: './company-detail.component.html',
  styleUrls: ['./company-detail.component.css']
})
export class CompanyDetailComponent implements OnInit {
  @Input()  job: Job;
  company: Company;
  editCompany: Company = null;

  updateCompany(form: NgForm) {
    this.companyService.update(this.editCompany, this.job.id).subscribe(
      (data) => {
        this.company = data;
        this.editCompany = null;
      },
      (err) => console.log(err)
    );
  }
  constructor(private companyService: CompanyService) { }

  ngOnInit() {
    this.company = this.job.company;
    if (!this.company.address) {
      this.company.address = new Address();
    }
  }

}
