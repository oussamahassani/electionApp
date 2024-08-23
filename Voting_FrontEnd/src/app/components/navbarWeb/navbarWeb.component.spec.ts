import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarWebComponent } from './navbarWeb.component';

describe('NavbarComponent', () => {
  let component: NavbarWebComponent;
  let fixture: ComponentFixture<NavbarWebComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NavbarWebComponent]
    });
    fixture = TestBed.createComponent(NavbarWebComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
