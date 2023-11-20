/*******************************************************************************
 * Copyright (c) 2023 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/

import { theme } from '@eclipse-sirius/sirius-components-core';
import { Theme, createTheme } from '@material-ui/core/styles';

export const baseTheme: Theme = createTheme({
  ...theme,
  palette: {
    type: 'light',
    primary: {
      main: '#FAA200',
      dark: '#AD7100',
      light: '#F2C572',
    },
    secondary: {
      main: '#012340',
      dark: '#012340',
      light: '#012340',
    },
    text: {
      primary: '#261E58',
      disabled: '#B3BFC5',
      hint: '#B3BFC5',
    },
    error: {
      main: '#DE1000',
      dark: '#9B0B00',
      light: '#E43F33',
    },
    divider: '#B3BFC5',
    navigation: {
      leftBackground: '#01234080',
      rightBackground: '#02396080',
    },
    action: {
      hover: '#01234026',
      selected: '#01234042',
    },
  },
  props: {
    MuiAppBar: {
      color: 'secondary',
    },
  },
  overrides: {
    MuiSnackbarContent: {
      root: {
        backgroundColor: '#7269A4',
      },
    },
  },
});

export const sysonTheme = createTheme(
  {
    overrides: {
      MuiAvatar: {
        colorDefault: {
          backgroundColor: baseTheme.palette.primary.main,
        },
      },
    },
  },
  baseTheme
);
