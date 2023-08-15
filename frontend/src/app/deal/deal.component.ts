import {Component, OnInit, AfterViewInit, ViewChild, TemplateRef} from '@angular/core';
import { UserService } from '../svc/user.service';
import { ClientService } from '../svc/client.service';
import { RealtorService } from '../svc/realtor.service';
import { RealEstateService } from '../svc/realEstate.service';
import { DealTypeService } from '../svc/dealType.service';
import { DealService } from '../svc/deal.service';
import { TokenStorageService } from '../svc/token-storage.service';
import { MatDialog } from '@angular/material/dialog';
import { NgModel } from '@angular/forms';
import { MatInput } from '@angular/material/input';
import { MatSort } from '@angular/material/sort';
import { MatTable} from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatProgressSpinner } from '@angular/material/progress-spinner';

@Component({
  selector: 'app-monitor',
  templateUrl: './deal.component.html',
  styleUrls: ['./deal.component.css']
})
export class DealComponent implements OnInit, AfterViewInit {
  error?: string;
  books?: Array<any> = [];
  role?: string = null;
  author: any = null;
  authors: Array<any> = [];
  client: any = null;
  clients: Array<any> = [];
  realtor: any = null;
  realtors: Array<any> = [];
  realEstate: any = null;
  realEstates: Array<any> = [];
  deal: any = null;
  deals: Array<any> = [];
  dealType: any = null;
  dealTypes: Array<any> = [];
  af: any = null;

  @ViewChild('dialogRef')
  dialogRef!: TemplateRef<any>;

  constructor(private userService: UserService,
              private clientService: ClientService,
              private realtorService: RealtorService,
              private realEstateService: RealEstateService,
              private dealTypeService: DealTypeService,
              private dealService: DealService,
              private tokenStorageService: TokenStorageService,
              public dialog: MatDialog) {
  }


  ngOnInit(): void {
    this.refreshRole();
    /*  let afComponent = new AfComponent(this.userService,
        this.afService,
        this.tokenStorageService,
        this.bookService,
        this.dialog);*/

  }

  editAf(af: any): void {
    //   this.afComponent.showDialog(af)
  }


  ngAfterViewInit(): void {
    this.refreshRole();
    if (this.role) {
      this.reloadDeals();
    } else {
      this.error = "Forbidden";
    }
  }

  openDialog(id?: number): void {
    if (id) {
      this.dealService.getDeal(id).subscribe(
        data => {
          this.clientService.getClient(data.client_id).subscribe(
            data => {
              this.client = data;
            },
            err => {
              alert(err.message);
            }
          );
          this.realtorService.getRealtor(data.realtor_id).subscribe(
            data => {
              this.realtor = data;
            },
            err => {
              alert(err.message);
            }
          );
          this.realEstateService.getRealEstate(data.real_estate_id).subscribe(
            data => {
              this.realEstate = data;
            },
            err => {
              alert(err.message);
            }
          );
          this.dealTypeService.getDealType(data.deal_type_id).subscribe(
            data => {
              this.dealType = data;
            },
            err => {
              alert(err.message);
            }
          );
          this.showDialog(this.deal = data);
        },

        err => {
          alert(err.message);
        }
      );
    } else {
      this.showDialog(this.deal = { commission: "" });
    }
  }




  private showDialog(deal: any) {
    const dialog = this.dialog.open(this.dialogRef, { data: deal });
    dialog.afterClosed().subscribe((res) => {
      if (res == "save") {
        /*  this.book.author_id = this.author.author_id;
          const result = this.book.book_id ? this.bookService.updateBook(this.book) : this.bookService.createBook(this.book);
          result.subscribe(() => this.reloadBooks(), err => this.error = err.message);*/
      }
    });
  }
  save(client_id: NgModel, realtor_id: NgModel,real_estate_id: NgModel,deal_type_id: NgModel): void {
    this.deal.client_id = Number(client_id.model);
    this.deal.realtor_id = Number(realtor_id.model);
    this.deal.real_estate_id = Number(real_estate_id.model);
    this.deal.deal_type_id = Number(deal_type_id.model);
    const result = this.deal.id ? this.dealService.updateDeal(this.deal) : this.dealService.createDeal(this.deal);
    result.subscribe(() => this.reloadDeals(), err => this.error = err.message);
  }

  delete(id: number): void {
    this.dealService.deleteDeal(id).subscribe(() => this.reloadDeals(), err => this.error = err.message);
  }

  private refreshRole() {
    this.error = undefined;
    let user = this.tokenStorageService.getUser();
    this.role = user ? user.role : undefined;
  }

  private reloadDeals() {
    this.dealService.getDeals().subscribe(
      data => {
        this.deals = data;
        this.clientService.getClients().subscribe(
          data => {
            this.clients = data;
          },
          err => {
            this.error = err.message;
          });

        this.realtorService.getRealtors().subscribe(
          data => {
            this.realtors = data;
          },
          err => {
            this.error = err.message;
          });
        this.realEstateService.getRealEstates().subscribe(
          data => {
            this.realEstates = data;
          },
          err => {
            this.error = err.message;
          });
        this.dealTypeService.getDealTypes().subscribe(
          data => {
            this.dealTypes = data;
          },
          err => {
            this.error = err.message;
          });
      },
      err => {
        this.error = err.message;
      }
    );
  }
}
