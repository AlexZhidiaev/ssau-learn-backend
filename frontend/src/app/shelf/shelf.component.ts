import {Component, OnInit, AfterViewInit, ViewChild, TemplateRef} from '@angular/core';
import { UserService } from '../svc/user.service';
import { BookService } from '../svc/book.service';
import { TokenStorageService } from '../svc/token-storage.service';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-shelf',
  templateUrl: './shelf.component.html',
  styleUrls: ['./shelf.component.css']
})
export class ShelfComponent implements OnInit, AfterViewInit {
  error?: string;
  books?: Array<any> = [];
  role?: string = null;
  book: any = null;

  @ViewChild('dialogRef')
  dialogRef!: TemplateRef<any>;

  constructor(private userService: UserService,
              private bookService: BookService,
              private tokenStorageService: TokenStorageService,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    this.refreshRole();
  }

  ngAfterViewInit(): void {
    this.refreshRole();
    if (this.role) {
      this.reloadBooks();
    } else {
      this.error = "Forbidden";
    }
  }

  openDialog(id?: number): void {
    if (id) {
      this.bookService.getBook(id).subscribe(
        data => {
          this.showDialog(this.book = data);
        },
        err => {
          alert(err.message);
        }
      );
    } else {
      this.showDialog(this.book = { name: "" });
    }
  }

  private showDialog(book: any) {
    const dialog = this.dialog.open(this.dialogRef, { data: book });
    dialog.afterClosed().subscribe((res) => {
      if (res == "save") {
        const result = this.book.id ? this.bookService.updateBook(this.book) : this.bookService.createBook(this.book);
        result.subscribe(() => this.reloadBooks(), err => this.error = err.message);
      }
    });
  }

  delete(id: number): void {
    this.bookService.deleteBook(id).subscribe(() => this.reloadBooks(), err => this.error = err.message);
  }

  private refreshRole() {
    this.error = null;
    let user = this.tokenStorageService.getUser();
    this.role = user ? user.role : null;
  }

  private reloadBooks() {
    this.bookService.getBooks().subscribe(
      data => {
        this.books = data;
      },
      err => {
        this.error = err.message;
      }
    );
  }
}
