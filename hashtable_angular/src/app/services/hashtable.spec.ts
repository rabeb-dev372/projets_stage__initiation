import { TestBed } from '@angular/core/testing';

import { Hashtable } from './hashtable';

describe('Hashtable', () => {
  let service: Hashtable;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Hashtable);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
