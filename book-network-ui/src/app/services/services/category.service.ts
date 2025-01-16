import { Injectable } from '@angular/core';
import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CategoryService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `saveFeedback()` */
  static readonly SaveCategoryPath = '/feedbacks';

}
