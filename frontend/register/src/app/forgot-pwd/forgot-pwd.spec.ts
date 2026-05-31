import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForgotPwd } from './forgot-pwd';

describe('ForgotPwd', () => {
  let component: ForgotPwd;
  let fixture: ComponentFixture<ForgotPwd>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ForgotPwd]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ForgotPwd);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
