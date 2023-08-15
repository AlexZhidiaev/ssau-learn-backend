import {Component, OnInit, AfterViewInit, ViewChild, TemplateRef} from '@angular/core';
import { UserService } from '../svc/user.service';
import { ClientService } from '../svc/client.service';
import { TokenStorageService } from '../svc/token-storage.service';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-monitor',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit, AfterViewInit {
  error?: string;
  clients?: Array<any> = [];
  role?: string = null;
  client: any = null;

  @ViewChild('dialogRef')
  dialogRef!: TemplateRef<any>;

  constructor(private userService: UserService,
              private clientService: ClientService,
              private tokenStorageService: TokenStorageService,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    this.refreshRole();
  }

  ngAfterViewInit(): void {
    this.refreshRole();
    if (this.role) {
      this.reloadClients();
    } else {
      this.error = "Forbidden";
    }
  }




  openDialog(id?: number): void {
    if (id) {
      this.clientService.getClient(id).subscribe(
        data => {
          this.showDialog(this.client = data);
        },
        err => {
          alert(err.message);
        }
      );
    } else {
      this.showDialog(this.client = { number: "" });
    }
  }

  private showDialog(client: any) {
    const dialog = this.dialog.open(this.dialogRef, { data: client });
    dialog.afterClosed().subscribe((res) => {

      if (res == "save") {
        const result = this.client.id ? this.clientService.updateClient(this.client) : this.clientService.createClient(this.client);
        result.subscribe(() => this.reloadClients(), err => this.error = err.message);
      }
    });
  }

  delete(id: number): void {
    this.clientService.deleteClient(id).subscribe(() => this.reloadClients(), err => {
    alert('Не удалось выполнить удаление')
    }
    );
  }

  private refreshRole() {
    this.error = undefined;
    let user = this.tokenStorageService.getUser();
    this.role = user ? user.role : undefined;
  }

  private reloadClients() {
    this.clientService.getClients().subscribe(
      data => {
        this.clients = data;
      },
      err => {
        this.error = err.message;
      }
    );
  }
}
