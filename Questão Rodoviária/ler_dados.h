int carregar_fila(tipo_fila *fila) {
  FILE *file = fopen("dados.txt", "r");
  if (file == NULL) {
    printf("Erro na abertura do arquivo");
    return 0;
  }

  char data[8], hora[4], placa[7];
  onibus o;

       while (1) {
          fscanf(file, "%s %s %s", o.data, o.hora, o.placa);
          o.tempo_espera=0;
          o.tempo_limite=0;
          if (!feof(file))
             entrarElemento(fila, o);
          else
             break;
       }

  fclose(file);

  return 1;
}