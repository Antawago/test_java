import { TestBed, async, inject } from '@angular/core/testing';

import { AuthorizerGuard } from './authorizer.guard';

describe('AuthorizerGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AuthorizerGuard]
    });
  });

  it('should ...', inject([AuthorizerGuard], (guard: AuthorizerGuard) => {
    expect(guard).toBeTruthy();
  }));
});
