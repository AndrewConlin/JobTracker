import { TestBed, inject } from '@angular/core/testing';

import { GoogleKnowledgeGraphService } from './google-knowledge-graph.service';

describe('GoogleKnowledgeGraphService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GoogleKnowledgeGraphService]
    });
  });

  it('should be created', inject([GoogleKnowledgeGraphService], (service: GoogleKnowledgeGraphService) => {
    expect(service).toBeTruthy();
  }));
});
