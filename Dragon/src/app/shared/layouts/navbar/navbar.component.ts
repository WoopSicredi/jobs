import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }
  logout(e: Event) {
    e.preventDefault();
    localStorage.removeItem('currentUser');
    setTimeout(() => {
      this.router.navigate(['/account/login']);
    }, 1000);
  }

}
