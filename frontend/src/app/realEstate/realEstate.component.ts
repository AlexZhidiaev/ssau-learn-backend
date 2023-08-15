import {Component, OnInit, AfterViewInit, ViewChild, TemplateRef} from '@angular/core';
import { UserService } from '../svc/user.service';
import { RealEstateService } from '../svc/realEstate.service';
import { TokenStorageService } from '../svc/token-storage.service';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-monitor',
  templateUrl: './realEstate.component.html',
  styleUrls: ['./realEstate.component.css']
})
export class RealEstateComponent implements OnInit, AfterViewInit {
  error?: string;
  realEstates?: Array<any> = [];
  role?: string = null;
  realEstate: any = null;

  @ViewChild('dialogRef')
  dialogRef!: TemplateRef<any>;

  @ViewChild('dialogCost')
  dialogCost!: TemplateRef<any>;

  constructor(private userService: UserService,
              private realEstateService: RealEstateService,
              private tokenStorageService: TokenStorageService,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    this.refreshRole();
  }

  ngAfterViewInit(): void {
    this.refreshRole();
    if (this.role) {
      this.reloadRealEstates();
    } else {
      this.error = "Forbidden";
    }
  }

  openDialog(id?: number): void {
    if (id) {
      this.realEstateService.getRealEstate(id).subscribe(
        data => {
          this.showDialog(this.realEstate = data);
        },
        err => {
          alert(err.message);
        }
      );
    } else {
      this.showDialog(this.realEstate = { number: "" });
    }
  }

  private showDialog(realEstate: any) {
    const dialog = this.dialog.open(this.dialogRef, { data: realEstate });
    dialog.afterClosed().subscribe((res) => {

      if (res == "save") {
        const result = this.realEstate.id ? this.realEstateService.updateRealEstate(this.realEstate)
          : this.realEstateService.createRealEstate(this.realEstate);
        result.subscribe(() => this.reloadRealEstates(), err => this.error = err.message);
      }
    });
  }

  private showCost(realEstate: any){
    const dialog = this.dialog.open(this.dialogCost, {data:realEstate});
    dialog.afterClosed().subscribe((res) => {

      if (res == "evaluate") {
        const result = this.realEstate.id ? this.realEstateService.evaluateRealEstate(this.realEstate)
          : this.realEstateService.createRealEstate(this.realEstate);
        result.subscribe(() => this.reloadRealEstates(), err => this.error = err.message);
      }
    });
  }

  openCost(id?: number): void {
    if (id) {
      this.realEstateService.getRealEstate(id).subscribe(
        data => {
          this.showCost(this.realEstate = data);
        },
        err => {
          alert(err.message);
        }
      );
    } else {
      this.showCost(this.realEstate = { number: "" });
    }
  }


  delete(id: number): void {
    this.realEstateService.deleteRealEstate(id).subscribe(() => this.reloadRealEstates(), err => this.error = err.message);
  }

  private refreshRole() {
    this.error = undefined;
    let user = this.tokenStorageService.getUser();
    this.role = user ? user.role : undefined;
  }

  private reloadRealEstates() {
    this.realEstateService.getRealEstates().subscribe(
      data => {
        this.realEstates = data;
      },
      err => {
        this.error = err.message;
      }
    );
  }


}
