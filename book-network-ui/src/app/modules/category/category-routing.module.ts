import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoryComponent } from './category.component';
import { MainComponent } from '../book/pages/main/main.component';
import { authGuard } from 'src/app/services/guard/auth.guard';
import { ListCategoryComponent } from './pages/list-category/list-category.component';
import { NotFoundComponent } from '../book/pages/not-found/not-found.component';
import { ManageCategoryComponent } from './pages/manage-category/manage-category.component';

const routes: Routes = [
{
    path: '',
    component: MainComponent,
    canActivate: [authGuard],
    children: [
      
      {
        path: '',
        component: ListCategoryComponent,
        canActivate: [authGuard]
      },

      {
        path: 'manage',
        component: ManageCategoryComponent,
        canActivate: [authGuard]
      },
 

    ]
  },
  { path: '**', component: NotFoundComponent } 



];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CategoryRoutingModule { }
