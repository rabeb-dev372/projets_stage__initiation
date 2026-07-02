import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HashtableVisualizer} from './hashtable-visualizer/hashtable-visualizer'
import { Hashtable} from './services/hashtable';
@Component({
  selector: 'app-root',
  standalone:true,
  imports: [RouterOutlet,HashtableVisualizer],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('hashtable');
  constructor(public hashtable: Hashtable){}
}
