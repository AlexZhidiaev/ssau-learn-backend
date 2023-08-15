import {Component, OnInit, AfterViewInit, ViewChild, TemplateRef} from '@angular/core';
import { UserService } from '../svc/user.service';
import { DealTypeService } from '../svc/dealType.service';
import { TokenStorageService } from '../svc/token-storage.service';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-monitor',
  templateUrl: './dealType.component.html',
  styleUrls: ['./dealType.component.css']
})
export class DealTypeComponent implements OnInit, AfterViewInit {
  error?: string;
  dealTypes?: Array<any> = [];
  role?: string = null;
  dealType: any = null;

  @ViewChild('dialogRef')
  dialogRef!: TemplateRef<any>;

  constructor(private userService: UserService,
              private dealTypeService: DealTypeService,
              private tokenStorageService: TokenStorageService,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    this.refreshRole();
  }

  ngAfterViewInit(): void {
    this.refreshRole();
    if (this.role) {
      this.reloadDealTypes();
    } else {
      this.error = "Forbidden";
    }
  }

  openDialog(id?: number): void {

    if (id) {

      this.dealTypeService.getDealType(id).subscribe(
        data => {
          this.showDialog(this.dealType = data);
        },
        err => {
          alert(err.message);
        }
      );
    } else {

      this.showDialog(this.dealType = { number: "" });
    }
  }

  private showDialog(dealType: any) {
    const dialog = this.dialog.open(this.dialogRef, { data: dealType });
    dialog.afterClosed().subscribe((res) => {

      if (res == "save") {
        const result = this.dealType.id ? this.dealTypeService.updateDealType(this.dealType) : this.dealTypeService.createDealType(this.dealType);
        result.subscribe(() => this.reloadDealTypes(), err => this.error = err.message);
      }
    });
  }

  delete(id: number): void {
    this.dealTypeService.deleteDealType(id).subscribe(() => this.reloadDealTypes(), err => this.error = err.message);
  }

  private refreshRole() {
    this.error = undefined;
    let user = this.tokenStorageService.getUser();
    this.role = user ? user.role : undefined;
  }

  private reloadDealTypes() {
    this.dealTypeService.getDealTypes().subscribe(
      data => {
        this.dealTypes = data;
      },
      err => {
        this.error = err.message;
      }
    );
  }
}
