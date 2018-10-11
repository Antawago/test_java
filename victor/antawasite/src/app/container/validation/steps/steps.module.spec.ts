import { StepsModule } from './steps.module';

describe('StepsModule', () => {
  let stepsModule: StepsModule;

  beforeEach(() => {
    stepsModule = new StepsModule();
  });

  it('should create an instance', () => {
    expect(stepsModule).toBeTruthy();
  });
});
