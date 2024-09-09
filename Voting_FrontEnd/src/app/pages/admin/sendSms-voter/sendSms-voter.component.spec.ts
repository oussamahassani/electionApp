import { ComponentFixture, TestBed } from '@angular/core/testing';

import {
  sendSmsvoterComponent
} from './sendSms-voter.component';

describe('RegisterComponent', () => {
  let component: sendSmsvoterComponent;
  let fixture: ComponentFixture<sendSmsvoterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [sendSmsvoterComponent]
    });
    fixture = TestBed.createComponent(sendSmsvoterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
