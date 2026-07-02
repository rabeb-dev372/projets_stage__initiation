import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HashtableVisualizer } from './hashtable-visualizer';

describe('HashtableVisualizer', () => {
  let component: HashtableVisualizer;
  let fixture: ComponentFixture<HashtableVisualizer>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HashtableVisualizer],
    }).compileComponents();

    fixture = TestBed.createComponent(HashtableVisualizer);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
