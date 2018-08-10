import { JobStatus } from './job-status';
import { Company } from './company';

export class Job {
  id: number;
  title: string;
  salary: number;
  postUrl: string;
  description: string;
  applicationDate: Date;
  offerDate: Date;
  status: JobStatus;
  company: Company;
  // add contacts

  constructor() {
    this.status = new JobStatus();
    this.company = new Company();
  }
}
