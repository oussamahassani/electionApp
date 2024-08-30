import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddvoterComponent } from './add-voter.component';

describe('RegisterComponent', () => {
  let component: AddvoterComponent;
  let fixture: ComponentFixture<AddvoterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddvoterComponent]
    });
    fixture = TestBed.createComponent(AddvoterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
