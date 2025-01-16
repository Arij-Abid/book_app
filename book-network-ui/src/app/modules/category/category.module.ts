import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CategoryRoutingModule } from './category-routing.module';
import { CategoryComponent } from './category.component';
import { ListCategoryComponent } from './pages/list-category/list-category.component';
import { ManageCategoryComponent } from './pages/manage-category/manage-category.component';


@NgModule({
  declarations: [
    CategoryComponent,
    ListCategoryComponent,
    ManageCategoryComponent
  ],
  imports: [
    CommonModule,
    CategoryRoutingModule
  ]
})
export class CategoryModule { }
