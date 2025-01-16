import { Component, HostListener } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent {


showButton = false;

 scrollToTop(): void {
  window.scrollTo({ top: 0, behavior: 'smooth' });
}

@HostListener('window:scroll', ['$event'])
onWindowScroll() {
  const scrollPosition = window.scrollY;
  const button = document.querySelector('.scroll-to-top') as HTMLElement;
  if (scrollPosition > 300) {
    button.style.display = 'block'; 
  } else {
    button.style.display = 'none'; 
  }
}
}