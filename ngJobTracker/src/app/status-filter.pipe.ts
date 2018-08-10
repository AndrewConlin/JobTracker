import { Pipe, PipeTransform } from '@angular/core';
import { Job } from './models/job';

@Pipe({
  name: 'statusFilter'
})
export class StatusFilterPipe implements PipeTransform {

  transform(jobs: Job[], categoryName: string): any {
    if (!categoryName || categoryName === 'All') {
      return jobs;
    }

    const results = [];

    jobs.forEach(element => {
      if (element.status.status === categoryName) {
        results.push(element);
      }
    });

    return results;
  }

}
