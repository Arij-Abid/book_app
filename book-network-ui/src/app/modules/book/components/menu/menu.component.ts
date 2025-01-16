import {Component, OnDestroy, OnInit} from '@angular/core';
import {KeycloakService} from '../../../../services/keycloak/keycloak.service';
import * as SockJs from 'sockjs-client';
import * as Stomp from 'stompjs';
import { Notification } from './notification';
import { Toast, ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit , OnDestroy {
  userName: string = '';
socketClient: any=null;
private notificationSubscription:any;
unreadNotificationCount =0;
notifications: Array<Notification> = [];

  constructor(
    private keycloakService: KeycloakService,  
    private toastService:ToastrService
  ) {
    }
    ngOnInit(): void {
      this.userName = this.keycloakService.getUserName();
      this.navigationHandler();
      if(this.keycloakService.keycloak.tokenParsed?.sub)
      {

        let ws = new SockJs('http://localhost:8085/api/v1/ws');
        this.socketClient = Stomp.over(ws); 
        this.socketClient.connect({'Authorization:': 'Bearer ' + this.keycloakService.keycloak.token},
      ()=>{
        console.log('connect to web ws')
this.notificationSubscription = this.socketClient.subscribe(
  `/user/${this.keycloakService.keycloak.tokenParsed?.sub}/notifications`,
  (message:any) =>  {
    const notification: Notification = JSON.parse(message.body);
    if(notification)
    {
      this.notifications.unshift(notification);
      switch (notification.status) {
        case 'BORROWED':
          this.toastService.info(notification.message, notification.bookTitle);
          break;
          case 'RETURNED':
            this.toastService.warning(notification.message, notification.bookTitle);
            break;
            case 'RETURN_APPROVED':
              this.toastService.success(notification.message, notification.bookTitle);
              break;
      }
      this.unreadNotificationCount++;

    }
  }
);    });
      }
  
    }
    private navigationHandler ()
    {
        const linkColor = document.querySelectorAll('.nav-link');
      linkColor.forEach(link => {
        if (window.location.href.endsWith(link.getAttribute('href') || '')) {
          link.classList.add('active');
        }
        link.addEventListener('click', () => {
          linkColor.forEach(l => l.classList.remove('active'));
          link.classList.add('active');
        });
      });
 
    }


  async logout() {
    await this.keycloakService.logout();
  }

  async accountManagement() {
    await this.keycloakService.accountManagement();
  }

  ngOnDestroy(): void {
    if (this.notificationSubscription) {
      this.notificationSubscription.unsubscribe();
    }
  }
  
}
