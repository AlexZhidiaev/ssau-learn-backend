import {Component, OnInit, AfterViewInit, ViewChild, TemplateRef} from '@angular/core';
import { UserService } from '../svc/user.service';
import { RealtorService } from '../svc/realtor.service';
import { TokenStorageService } from '../svc/token-storage.service';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-monitor',
  templateUrl: './realtor.component.html',
  styleUrls: ['./realtor.component.css']
})
export class RealtorComponent implements OnInit, AfterViewInit {
  error?: string;
  realtors?: Array<any> = [];
  role?: string = null;
  realtor: any = null;

  @ViewChild('dialogRef')
  dialogRef!: TemplateRef<any>;

  constructor(private userService: UserService,
              private realtorService: RealtorService,
              private tokenStorageService: TokenStorageService,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    this.refreshRole();
  }

  ngAfterViewInit(): void {
    this.refreshRole();
    if (this.role) {
      this.reloadRealtors();
    } else {
      this.error = "Forbidden";
    }
  }

  openDialog(id?: number): void {

    if (id) {

      this.realtorService.getRealtor(id).subscribe(
        data => {
          this.showDialog(this.realtor = data);
        },
        err => {
          alert(err.message);
        }
      );
    } else {

      this.showDialog(this.realtor = { number: "" });
    }
  }

  private showDialog(realtor: any) {
    const dialog = this.dialog.open(this.dialogRef, { data: realtor });
    dialog.afterClosed().subscribe((res) => {

      if (res == "save") {
        const result = this.realtor.id ? this.realtorService.updateRealtor(this.realtor) : this.realtorService.createRealtor(this.realtor);
        result.subscribe(() => this.reloadRealtors(), err => this.error = err.message);
      }
    });
  }

  delete(id: number): void {
    this.realtorService.deleteRealtor(id).subscribe(() => this.reloadRealtors(), err => this.error = err.message);
  }

  private refreshRole() {
    this.error = undefined;
    let user = this.tokenStorageService.getUser();
    this.role = user ? user.role : undefined;
  }

  private reloadRealtors() {
    this.realtorService.getRealtors().subscribe(
      data => {
        this.realtors = data;
      },
      err => {
        this.error = err.message;
      }
    );
  }
}
