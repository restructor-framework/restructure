const $_documentContainer = document.createElement('template');

$_documentContainer.innerHTML = `

<custom-style>
  <style>

html {
  --lumo-primary-text-color: rgb(114, 22, 243);
  --lumo-primary-color-50pct: rgba(114, 22, 243, 0.5);
  --lumo-primary-color-10pct: rgba(114, 22, 243, 0.1);
  --lumo-error-text-color: rgb(255, 56, 103);
  --lumo-error-color-50pct: rgba(255, 56, 103, 0.5);
  --lumo-error-color-10pct: rgba(255, 56, 103, 0.1);
  --lumo-success-text-color: rgb(20, 184, 143);
  --lumo-success-color-50pct: rgba(20, 184, 143, 0.5);
  --lumo-success-color-10pct: rgba(20, 184, 143, 0.1);
  --lumo-primary-color: hsl(265, 90%, 52%);
  --lumo-error-color: hsl(346, 100%, 61%);
  --lumo-success-color: hsl(165, 80%, 40%);
  --lumo-header-text-color: hsl(285, 35%, 15%);
  --lumo-body-text-color: hsla(285, 40%, 16%, 0.94);
  --lumo-secondary-text-color: hsla(285, 42%, 18%, 0.72);
  --lumo-tertiary-text-color: hsla(285, 45%, 20%, 0.5);
  --lumo-disabled-text-color: hsla(285, 50%, 22%, 0.26);
}

[theme~="dark"] {
}

  </style>
</custom-style>`


document.head.appendChild($_documentContainer.content);