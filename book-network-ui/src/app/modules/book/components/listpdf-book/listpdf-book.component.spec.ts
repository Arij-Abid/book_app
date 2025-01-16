import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListpdfBookComponent } from './listpdf-book.component';

describe('ListpdfBookComponent', () => {
  let component: ListpdfBookComponent;
  let fixture: ComponentFixture<ListpdfBookComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListpdfBookComponent]
    });
    fixture = TestBed.createComponent(ListpdfBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
